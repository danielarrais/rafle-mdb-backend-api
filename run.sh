locust -f loadteste.py --master --host=http://localhost:8080 --headless --users 5000 --spawn-rate 5000

# Workers (executar em paralelo)
for i in {1..10}; do
  locust -f loadteste.py --worker --master-host=127.0.0.1 &
done