import {Component, OnInit , Input , Output, EventEmitter} from '@angular/core';
import { ParentService } from 'src/app/parent.service';
import { IParent } from 'src/app/parent';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-parent',
  templateUrl: './modal-parent.html'
})
export class NgbdModalParent implements OnInit {
  closeResult: string;
  public parents: IParent[]=[];
  constructor(private modalService: NgbModal, private _parentService: ParentService) {}
  seletecParentId: string="1";
  ngOnInit() {
    this._parentService.getParentList()
    .subscribe(data => this.parents = data);
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
      this.messageEvent.emit(this.seletecParentId);
  }
}