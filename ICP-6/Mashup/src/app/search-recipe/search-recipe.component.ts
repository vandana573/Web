import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  formattedaddress = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {                                /* For retrieving details of the item*/
    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;
    if (this.recipeValue !== null) {             /* For retrieving food which matches with the search*/
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=d0159eaa&app_key=\n' + 'cb439227a6194ebb0695233578ce664c').subscribe((recipes: any) => {
        this.recipeList = Object.keys(recipes.hits).map(function (rec) {
          const recipe = recipes.hits[rec].recipe;
          console.log(recipe.digest[0].schemaOrgTag);
          return {name: recipe.label, content: recipe.digest[0].schemaOrgTag, icon: recipe.image, add: recipe.address, url: recipe.url};
        });
      });
    }
    if (this.placeValue != null) {                   /* For retrieving location of the item*/
      if (this.placeValue !== '') {
        this._http.get('https://api.foursquare.com/v2/venues/search?client_id=VGMF0FMI0TS02L1GBNMZNQOIYC5UVPZG5P5CXGH3INQM1531' +
            // tslint:disable-next-line:max-line-length
            '&client_secret=SDVG0L0XWCCRHGKRG0HJNBKNAUVAEJD3LMNRGSQ0GEWQVYDB&v=20200625&near=' + this.placeValue + '&query=' + this.recipeValue).subscribe((restaurants: any) => {
          this.venueList = Object.keys(restaurants.response.venues).map(function (input) {
            const restaurant = restaurants.response.venues[input];
            return {
              name: restaurant.name,
              currentLat: restaurant.location.labeledLatLngs[0].lng,
              currentLong: restaurant.location.labeledLatLngs[0].lat,
              formattedAddress: restaurant.location.formattedAddress
            };

          });
        }, error => {
        });
      }
    }
  }
}
