import { Component, OnInit } from '@angular/core';
import { TaskManagerService } from 'src/app/task-manager.service';
import { ActivatedRoute, Router} from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { NgbdModalParent } from 'src/app/modal-parent';
import { ParentService } from 'src/app/parent.service';
import { ITask } from 'src/app/task';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {

  validMessage : string= "";
  taskeditform: FormGroup;
  parentVisble: boolean=true;
  public taskedit: ITask;
  public parentTaskEdit;
  constructor(private _taskManagerService: TaskManagerService,private _parentService: ParentService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.taskeditform = new FormGroup({
      taskId: new FormControl(),
      priority: new FormControl('', Validators.required),
      endDate: new FormControl(),
      startDate: new FormControl(),
      title: new FormControl('', Validators.required),
      parentTaskId: new FormControl(),
      parentTaskTitle: new FormControl()
    });
    this.getTaskedit(this.route.snapshot.params.id);
  }
  getTaskedit(id: number) {
    var _that = this;
    this._taskManagerService.getTask(id).subscribe(
      data => {
        _that.taskedit= data
        console.log("in getTaskedit "+this.taskedit.parentId);
        if(this.taskedit.parent==false){
          this.getParentTaskedit(this.taskedit.parentId);
        }else{
          this.populateView();
        }
      },
      err => console.error(err),
      () => console.log('Task Loaded for Editing'));
  }
  getParentTaskedit(id: number) {
    var _that = this;
    this._parentService.getParent(id).subscribe(
      data => {
        _that.parentTaskEdit= data
        this.populateView()
      },
      err => console.error(err),
      () => console.log('Parent Loaded for Editing'));
  }
  populateView(){
    this.taskeditform.controls['taskId'].setValue(this.taskedit.taskId);
    this.taskeditform.controls['priority'].setValue(this.taskedit.priority);
    this.taskeditform.controls['endDate'].setValue(this.taskedit.endDate);
    this.taskeditform.controls['startDate'].setValue(this.taskedit.startDate);
    this.taskeditform.controls['title'].setValue(this.taskedit.title);
    if(this.taskedit.parent==false){
      this.taskeditform.controls['parentTaskId'].setValue(this.parentTaskEdit.parentId);
      this.taskeditform.controls['parentTaskTitle'].setValue(this.parentTaskEdit.parentTask.title);
    }
    this.parentVisble=this.taskedit.parent; 
    console.log(this.taskeditform);
  }
  updateTask(){
    console.log(this.taskeditform);
    if(this.taskedit.parent==false){
     this.taskedit.parentId=this.taskeditform.value.parentTaskId;
    } 
    if(this.taskeditform.value.endDate!=null){
      this.taskedit.endDate=this.taskeditform.value.endDate;
      this.taskedit.status="Complete";
    } 
    this.taskedit.title=this.taskeditform.value.title;
    this.taskedit.priority=this.taskeditform.value.priority;
    this._taskManagerService.updateTask(this.taskedit).subscribe(
      data => {
        this.taskeditform.reset();
        this.router.navigate(['/viewtask']);
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )
      this.validMessage ="Update Success";
  }
  receiveMessage($event){
    this.getParentTaskedit($event);
  }
}
