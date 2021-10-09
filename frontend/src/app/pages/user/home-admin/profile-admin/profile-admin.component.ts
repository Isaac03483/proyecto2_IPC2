import { ProfileAdminService } from '../../../../services/user/profile-admin.service';
import { UsrService } from 'src/app/services/user/usr.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-admin',
  templateUrl: './profile-admin.component.html',
  styleUrls: ['./profile-admin.component.css']
})
export class ProfileAdminComponent implements OnInit {

  constructor(private service: UsrService, private profileAdminService: ProfileAdminService) { }

  ngOnInit(): void {

  }

  setOption(option: number){
    this.profileAdminService.option = option;
  }

  getOption(){
    return this.profileAdminService.option;
  }

}
