import { Component, OnInit } from '@angular/core';
import {Ad} from "../../../../../../../objects/classes/ads/Ad";
import {AdStatus} from "../../../../../../../objects/enums/ad/AdStatus";

@Component({
  selector: 'app-ad-viewer',
  templateUrl: './ad-viewer.component.html',
  styleUrls: ['./ad-viewer.component.css']
})
export class AdViewerComponent implements OnInit {

  adList: Array<Ad>=[];
  adStatusEnum = AdStatus;
  constructor() { }

  ngOnInit(): void {
  }

  activateAd(ad: Ad) {

  }

  updateAd(ad: Ad) {

  }
}
