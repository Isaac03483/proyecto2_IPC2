import {Component, Input, OnInit} from '@angular/core';
import {Magazine} from "../../../../../../objects/classes/magazine/Magazine";
import {MagazineSubscription} from "../../../../../../objects/enums/magazine/MagazineSubscription";
import {SearchMagazineService} from "../../../../../services/magazine/search-magazine.service";

@Component({
  selector: 'app-magazine-details',
  templateUrl: './magazine-details.component.html',
  styleUrls: ['./magazine-details.component.css']
})
export class MagazineDetailsComponent implements OnInit {

  @Input() magazine!: Magazine;
  magazineSubscription = MagazineSubscription;
  constructor(private service: SearchMagazineService) { }

  ngOnInit(): void {
  }

  showSubscriptionForm(number: number) {

    this.service.option = number;
  }
}
