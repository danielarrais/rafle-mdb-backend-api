from locust import HttpUser, task, between
import threading
import random

raffle_id = None
raffle_created = False
lock = threading.Lock()
request_count = 0
max_requests = 100_000_000
request_lock = threading.Lock()

raffle_payload = {
    "name": "Flamengo vs Vasco",
    "difficult": 0.92,
    "date": "2024-03-22",
    "items": [
        {"name": "Óculos", "type": "SIMPLE", "quantity": 1000000000},
        {"name": "Boné", "type": "SIMPLE", "quantity": 1000000000},
        {"name": "Par super prêmio", "type": "SUPER", "quantity": 1000000000}
    ]
}


def gerar_cpf():
    def calcular_dv(digs):
        soma = sum([(len(digs) + 1 - i) * int(num) for i, num in enumerate(digs)])
        resto = soma % 11
        return '0' if resto < 2 else str(11 - resto)

    # Gera os 9 primeiros dígitos aleatórios
    base = ''.join([str(random.randint(0, 9)) for _ in range(9)])

    # Calcula os dois dígitos verificadores
    dv1 = calcular_dv(base)
    dv2 = calcular_dv(base + dv1)

    return f"{base}{dv1}{dv2}"


class RaffleUser(HttpUser):
    wait_time = between(0.01, 0.05)

    def on_start(self):
        global raffle_created, raffle_id
        with lock:
            if not raffle_created:
                res = self.client.post("/raffle/", json=raffle_payload)
                if res.status_code in [200, 201]:
                    raffle_id = res.json().get("id")
                    raffle_created = True
                    print(f"✅ Sorteio criado: {raffle_id}")
                else:
                    print("❌ Erro ao criar sorteio:", res.status_code, res.text)
                    raise Exception("Erro ao criar sorteio")

    @task
    def draw(self):
        global request_count, max_requests, raffle_id

        if not raffle_id:
            return

        with request_lock:
            if request_count >= max_requests:
                self.environment.runner.quit()
                return
            request_count += 1

        cpf = gerar_cpf();

        user_payload = {
            "fullName": "Teste Bezerra e Silva",
            "personalID": cpf,
            "email": f"teste_{cpf}@gmail.com",
            "phone": f"{random.randint(1000000000, 9999999999)}",
            "city": "Sambaíba"
        }

        self.client.post(f"/user/draw/{raffle_id}", json=user_payload)
