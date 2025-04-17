# Raffle project

This project is based on one private project mine used to raffle prizes and collect leads. My IDEA in this project is
replicate my solution using Spring Boot and Mongo DB.

## The flow

1. The Admin inserts some raffle in the database calling the API `{{base_url}}/raffle/`:
   ```JSON
   {
      "name": "Raffle Name",
      "difficult": 0.92,
      "date": "2024-03-22",
      "items": [
          {
              "name": "Glasses",
              "type": "SIMPLE",
              "quantity": 10
          },
          {
              "name": "Cap",
              "type": "SIMPLE",
              "quantity": 10
          },
          {
              "name": "Iphone 16",
              "type": "SUPER",
              "quantity": 10
          }
      ]
   }
   ```
2. The user inserts the data in some form, and this form calls my another API `{{base_url}}/user/draw/:raffleId`
   ```JSON
   {
	    "fullName": "Teste Bezerra e Silva",
	    "personalID": "23423423434",
	    "email": "danielarrais@gmail.com",
	    "phone": "53453453453",
	    "city": "Sambaíba"
   }
   ```
3. The API inserts the new user and draws the prize in the same call, returning the prize information:
   ```json
   {
     "id": "680047ab5847d03256ec16ba",
     "fullName": "Teste Bezerra e Silva",
     "personalID": "23423423434",
     "email": "dsfsdfsdf@gmail.com",
     "phone": "53453453453",
     "city": "Sambaíba",
     "prize": {
       "raffleId": "67fc13efbce4aa7f5967d51f",
       "type": "SUPER",
       "name": "Par super prêmio",
       "status": "PENDING"
     }
   }
   ```
4. After drawing the item, the quantity of its is decreased in the item inside the prize document
5. For the users receive the prize the admin must validate the prize using the API `{{base_url}}/user/validate-prize/:personalID`
   ```json
   {
     "raffleId": "67fc13efbce4aa7f5967d51f",
     "type": "SIMPLE",
     "name": "Boné",
     "status": "WITHDRAWN"
   }
   ```

## Database structure

### Collection Prizes

```json
{
  "_id": {
    "$oid": "67fc13efbce4aa7f5967d51f"
  },
  "name": "Flamengo vs Vasco",
  "difficult": "0.92",
  "date": {
    "$date": "2024-03-22T03:00:00.000Z"
  },
  "items": [
    {
      "name": "Óculos",
      "type": "SIMPLE",
      "quantity": 999991168
    },
    {
      "name": "Boné",
      "type": "SIMPLE",
      "quantity": 999983378
    },
    {
      "name": "Par super prêmio",
      "type": "SUPER",
      "quantity": 999991371
    }
  ],
  "_class": "dev.danielarrais.raflebackendapi.raffle.RaffleDocument"
}
```

### Collection Users:

```json
{
  "_id": {
    "$oid": "67fc13f0bce4aa7f5967d528"
  },
  "fullName": "Teste Bezerra e Silva",
  "personalID": "60283428902",
  "email": "teste_98540293609@gmail.com",
  "phone": "9168759778",
  "city": "Sambaíba",
  "_class": "dev.danielarrais.raflebackendapi.user.UserDocument"
}
```