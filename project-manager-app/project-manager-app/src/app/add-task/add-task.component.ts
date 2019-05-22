import { Component, OnInit } from '@angular/core';
import { TaskManagerService } from 'src/app/task-manager.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { NgbdModalBasic } from 'src/app/modal-basic';
import { NgbdModalParent } from 'src/app/modal-parent';
import { NgbdModalUser } from 'src/app/modal-user';
import { ParentService } from 'src/app/parent.service';
import { ProjectService } from 'src/app/project.service';
import { UserService } from 'src/app/user.service';
import { DatePipe } from '@angular/common';
import { ITask } from 'src/app/task';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  validMessage : string= "";
  taskaddform: FormGroup;
  public taskadd;
  public filterProject;
  public filterParent;
  public filterUser;
  public taskinsert;
  public addTaskData={
    priority: 0,
    title: "",
    parentId: 0,
    projectId: 0,
    parent: false,
    userId: 0
  };

  pipe = new DatePipe('en-us');
  post: any;
  constructor(private _taskManagerService: TaskManagerService, private route: ActivatedRoute, private router: Router,  private _projectService: ProjectService,  private _parentService: ParentService, private _userService: UserService) { }
  ngOnInit() {
    const now =new Date();
    const myFormattedDate = this.pipe.transform(now, 'short');
    this.taskaddform = new FormGroup({
      priority: new FormControl('', Validators.required),
      title: new FormControl('', Validators.required),
      parentTaskId: new FormControl(),
      parentTitle: new FormControl(),
      projectTitle: new FormControl('', Validators.required),
      parentCheck: new FormControl(0,Validators.required),
      startDate: new FormControl(myFormattedDate, Validators.required),
      userId: new FormControl(),
      userName: new FormControl(),
      projectId: new FormControl()
    });
  }

  addTask(){
    var _that = this;
    if(this.taskaddform.value.parentCheck==1){
      this.addTaskData.parent=true;
    }else{
      this.addTaskData.parent=false; 
      this.addTaskData.parentId=this.taskaddform.value.parentTaskId;    
    }
    this.addTaskData.title=this.taskaddform.value.title;
    this.addTaskData.priority=this.taskaddform.value.priority;
    this.addTaskData.projectId=this.taskaddform.value.projectId;
    this.addTaskData.userId=this.taskaddform.value.userId;
    this.validMessage ="Create Success";
    this._taskManagerService.addTask(this.addTaskData).subscribe(
      data => {
        _that.taskinsert = data
        this.addParent();
        this.taskaddform.reset();
        this.router.navigate(['/viewtask']);
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )
  }
  addParent(){
    if(this.taskinsert.parent){
      var parentinsert={
        parentId:0 ,
        parentTaskId: this.taskinsert.taskId
      };
      this._parentService.addParent(parentinsert).subscribe(
        data => {
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
    }
  }
  
  getFilterProject(id: number) {
    var _that = this;
    this._projectService.getProject(id).subscribe(
      data => {
        _that.filterProject = data
        this.updateProjectFormField();
      },
      err => console.error(err),
      () => console.log('Project Loaded for View'));
  }
  receiveMessage($event) {
    console.log($event);
    if($event){
      this.getFilterProject($event);
    }else{
      this.taskaddform.controls['projectTitle'].setValue("");
      this.taskaddform.controls['projectId'].setValue("");
    }
  }
  updateProjectFormField(){
    this.taskaddform.controls['projectTitle'].setValue(this.filterProject.title);
    this.taskaddform.controls['projectId'].setValue(this.filterProject.projectId);
  }
  receiveParentMessage($event) {
    console.log($event);
    if($event){
      this.getFilterParent($event);
    }else{
      this.taskaddform.controls['parentTaskId'].setValue("");
      this.taskaddform.controls['parentTitle'].setValue("");
    }
  }
  getFilterParent(id: number) {
    var _that = this;
    this._parentService.getParent(id).subscribe(
      data => {
        _that.filterParent = data
        this.updateParentFormField();
      },
      err => console.error(err),
      () => console.log('Project Loaded for View'));
  }
  updateParentFormField(){
    this.taskaddform.controls['parentTaskId'].setValue(this.filterParent.parentId);
    this.taskaddform.controls['parentTitle'].setValue(this.filterParent.parentTask.title);
  }
  receiveUserMessage($event) {
    console.log($event);
    if($event){
      this.getFilterUser($event);
    }else{
      this.taskaddform.controls['userId'].setValue("");
      this.taskaddform.controls['userName'].setValue("");
    }
  }
  getFilterUser(id: number) {
    var _that = this;
    this._userService.getUser(id).subscribe(
      data => {
        _that.filterUser = data
        this.updateUserFormField();
      },
      err => console.error(err),
      () => console.log('User Loaded for View'));
  }
  updateUserFormField(){
    this.taskaddform.controls['userId'].setValue(this.filterUser.userId);
    this.taskaddform.controls['userName'].setValue(this.filterUser.firstName+" "+this.filterUser.lastName);
  }
}
