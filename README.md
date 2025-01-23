Installation Guide{
öppna en terminal och ta dig till den mapp där du vill ha projektet
git clone https://github.com/Ollie1994/plantswap.git
öppna projeket inuti inteilj
starta applicationen
starta postman - gör en  ny create (tex "name": "test") 
starta mongodb - kolla att det la till något nytt i db
}

Postman {
https://documenter.getpostman.com/view/40700206/2sAYQfBoYb
}

AffärsRegler {
- en användare kan max ha 10 annonser"plants".
- växter markerad för byta kan bara bytas mot andra växer "EJ SÄLJAS"
- vid ett byte så måste båda parterna godkänna bytet innan det går igenom
- prissatta växer får bara prissättas mellan 50 - 1000
}

Begränsningar {
Finns mer felhantering jag kunde gjort i tex update funktioner, skulle kunna förfinat min felhantering och 
lägga till GlobalExceptionHandler.
}

Framtida Förbättringar {
Defintivt finare metoder, och mindre duplicerad kod, samt GlobalExceptionHandler
}