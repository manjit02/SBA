import {Component, OnInit , Input , Output, EventEmitter} from '@angular/core';
import { UserService } from 'src/app/user.service';
import { IUser } from 'src/app/user';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-user',
  templateUrl: './modal-user.html'
})
export class NgbdModalUser implements OnInit {
  closeResult: string;
  public users: IUser[]=[];
  constructor(private modalService: NgbModal, private _userService: UserService) {}
  seletectedUserId: string="1";
  ngOnInit() {
    this._userService.getUsersList()
    .subscribe(data => this.users = data);
  }

  @Output() messageEvent = new EventEmitter<string>();

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
        console.log("");
      return  `with: ${reason}`;
    }
  }
  sendMessage(){
      this.messageEvent.emit(this.seletectedUserId);
  }
}