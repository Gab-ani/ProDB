# ProDB
Webscraping professional dota2 matches to database
_____________________________________________
Originally planned as parsing heavy project, now only partially due to rest api piece I found on one of the sites.
Has tricky htmUnit part to get javascript content tho.
_____________________________________________
The idea is to create a database of dota2 matches only containing at least one pro-player. Kinda what dotaprotracker does, but it focuses heavily on players, and I want to focus on matches and heroes more. Also protracker only stores mathes data for 8 days (replay expiration) and I want to store it for about a month. On the other hand, there is stratz site, but it doesn't show large histories of matches, it only shows you it if you had  replay code. So what my project does is uses protracker as ids provider and then request matches data from stratz via their API
