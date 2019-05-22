import {Component, OnInit , Input , Output, EventEmitter} from '@angular/core';
import { ProjectService } from 'src/app/project.service';
import { IProject } from 'src/app/project';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-basic',
  templateUrl: './modal-basic.html'
})
export class NgbdModalBasic implements OnInit {
  closeResult: string;
  public projects: IProject[]=[];
  constructor(private modalService: NgbModal, private _projectService: ProjectService) {}
  projectTitle: string;

  ngOnInit() {
    this._projectService.getProjectList()
    .subscribe(data => this.projects = data);
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
      this.messageEvent.emit(this.projectTitle);
  }

  clearFilter(){
    this.projectTitle="";
  }
}