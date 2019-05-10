# IP_project
School project for an company to make weekmenus

## Contents

### Frontend

* Add a dish (name nust be unique)
* Update a dish (Possible to update the price and type of dish)
* Delete a dish (Dish can only be deleted if it is not used in a dayMeny)

### REST service
* Check the weekMenu ( /weekmenu) &rarr; returns json with the weekmenu
* Make a new dayMenu ( /daymenu/add) &rarr; returns jeon with the created dayMenu

```JSON
{
	"dag": "Zondag",
	"datum": "28-04-2019",
	"soep":{
		"beschrijving": "Soep",
		"type": "Soep",
		"prijs": 1.5
	},
	"dagschotel":{
		"beschrijving": "Balletjes",
		"type": "Dagschotel",
		"prijs": 2.5
	},
	"veggie":{
		"beschrijving": "Rijst",
		"type": "Veggie",
		"prijs": 2.5
	}
}
```

* Update a dayMenu ( /dayMeny/update/{date-to-update} ) &rarr; returns the updated dayMenu in JSON

```URL
http://localhost:8080/dagmenu/change/28-04-2019
```

```JSON
[
    {
        "soep": {
            "beschrijving": "Soep",
            "prijs": 1.5,
            "type": "Soep"
        },
        "dagschotel": {
            "beschrijving": "Balletjes",
            "prijs": 2.5,
            "type": "Dagschotel"
        },
        "veggie": {
            "beschrijving": "Rijst",
            "prijs": 2.5,
            "type": "Veggie"
        },
        "datum": "2019-04-28",
        "dag": "Zondag"
    }
]
```
