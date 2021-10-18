import {Component, Input, OnInit} from '@angular/core';
import {Magazine} from "../../../../../../objects/classes/magazine/Magazine";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-magazine-details',
  templateUrl: './magazine-details.component.html',
  styleUrls: ['./magazine-details.component.css']
})
export class MagazineDetailsComponent implements OnInit {

  @Input() magazine!: Magazine;
  constructor() { }

  ngOnInit(): void {
  }

  showInformation() {
    window.alert("INFORMACIÓN DEL EDITOR.");
  }
}
