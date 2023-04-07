#Overview
This is a simple Monster Card Trading Game that functions over a  HTTP Webserver.
There are different types of cards ranging from Monster to Spell cards of different attributes and elements.
Elements interact in the following way:
- water > fire
- fire > normal
- normal > water
Some monsters and spells have the following interactions:
- Knights automatically lose against any Water Spell
- Kraken automatically wins against any Spell Card

#Techstack
There is no frontend to the applications so the curl requests are alternatively sent to the backend.
This app utilizes JPA and prepared statements to manage data in the database instead of object mappers.

#GitHub
https://github.com/denislesha28/Monster_Card_Trading_Game.git
