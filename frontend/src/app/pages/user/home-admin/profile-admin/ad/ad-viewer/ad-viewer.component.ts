import { Component, OnInit } from '@angular/core';
import {Ad} from "../../../../../../../objects/classes/ads/Ad";
import {AdStatus} from "../../../../../../../objects/enums/ad/AdStatus";
import {AdService} from "../../../../../../services/ads/ad.service";

@Component({
  selector: 'app-ad-viewer',
  templateUrl: './ad-viewer.component.html',
  styleUrls: ['./ad-viewer.component.css']
})
export class AdViewerComponent implements OnInit {

  adList: Array<Ad>=[];
  adStatusEnum = AdStatus;
  constructor(private adsService: AdService) { }

  ngOnInit(): void {
    this.getAdList()
  }

  getAdList(){
    this.adsService.getAdsInfo()
      .subscribe((data: Array<Ad>)=>{
        if(data != null){
          this.adList = data;
          console.log(this.adList)
        }
      })
  }

  activateAd(ad: Ad) {

    this.adsService.activateAd(ad)
      .subscribe((data: Ad)=>{
        if(data != null){
          this.getAdList();
        }
      })
  }

  updateAd(ad: Ad) {

  }
}
