import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { NgbdModalUser } from 'src/app/modal-user';
import { UserService } from 'src/app/user.service';
import { ProjectService } from 'src/app/project.service';
import { IProject } from 'src/app/project';
import { IUser } from 'src/app/user';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

 validMessage : string= "";
  projectaddform: FormGroup;
  public projectadd:IProject ;
  public filterUser: IUser;
  pipe = new DatePipe('en-us');
  post: any;
  public projects: IProject[] = [];
  showEdit:boolean =false;
  today =new Date();
  todayTemp =new Date;
  tomorrow=this.todayTemp.setDate(this.todayTemp.getDate()+1);
  formattedToomorrow=this.pipe.transform(this.tomorrow, 'yyyy-MM-dd');
  formattedToday = this.pipe.transform(this.today, 'yyyy-MM-dd'); 

  constructor(private route: ActivatedRoute, private router: Router,  private _projectService: ProjectService,  private _userService: UserService) { 
  this._projectService.projectUpdateSuccess.subscribe(
      (st: string) => {
        this.getAllProjects();
      }
    )
  }
 getAllProjects() {
    this._projectService.getProjectList()
      .subscribe(data => this.projects = data);
  }
  ngOnInit() {  
    this.projectaddform = new FormGroup({
      priority: new FormControl('', Validators.required),
      title: new FormControl('', Validators.required),
      setDateCheck: new FormControl(0,Validators.required),
      startDate: new FormControl(this.formattedToday),
      endDate: new FormControl(this.formattedToomorrow),
      projectManagerId: new FormControl(),
      userName: new FormControl(),
      projectId: new FormControl()
    });
	this.getAllProjects();
  }
  populateView(){
    this.projectaddform.controls['priority'].setValue(this.projectadd.priority);
    this.projectaddform.controls['title'].setValue(this.projectadd.title);
    this.projectaddform.controls['startDate'].setValue(this.projectadd.startDate);
    this.projectaddform.controls['endDate'].setValue(this.projectadd.endDate);
    this.projectaddform.controls['projectId'].setValue(this.projectadd.projectId);
  }
  addProject(){ 
    if(!this.projectaddform.value.startDate<=this.projectaddform.value.endDate
      && this.projectaddform.value.endDate.length>0){ 
        this.validMessage="Start Date should not be greater than end date";
        return;
      }  
      this._projectService.createproject(this.projectaddform.value).subscribe(
        data => {
          this._projectService.projectUpdateSuccess.emit('Project Updated Success');
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      )
    this.validMessage="";
    this.resetForm();
  }

  editProject(project: IProject){  
    this.showEdit=true;
    this.projectadd=project;
    this.getFilterUser(this.projectadd.projectManagerId);
    this.populateView();
    this.projectaddform.controls['setDateCheck'].setValue(1);
  }
  suspendProject(project: IProject){ 
    console.log("suspendProject");
    this._projectService.suspendproject(project).subscribe(
      data => {
        this._projectService.projectUpdateSuccess.emit('Project Suspended Success');
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )
  this.validMessage="";
  this.resetForm();
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
  receiveUserMessage($event) {
    if($event){
      this.getFilterUser($event);
    }else{
      this.projectaddform.controls['projectManagerId'].setValue("");
      this.projectaddform.controls['userName'].setValue("");
    }
  }
  updateUserFormField(){
    this.projectaddform.controls['projectManagerId'].setValue(this.filterUser.userId);
    this.projectaddform.controls['userName'].setValue(this.filterUser.firstName+" "+this.filterUser.lastName);
  }
  resetForm(){
    this.projectaddform = new FormGroup({
      priority: new FormControl('', Validators.required),
      title: new FormControl('', Validators.required),
      setDateCheck: new FormControl(0,Validators.required),
      startDate: new FormControl(this.formattedToday),
      endDate: new FormControl(this.formattedToomorrow),
      projectManagerId: new FormControl(),
      userName: new FormControl(),
      projectId: new FormControl()
    });
    this.showEdit=false;
    this.validMessage ="";
  }
  
  startDateSort() {
    this.projects
    .sort((a: IProject, b: IProject) => {
      return a.startDate > b.startDate ? 0 : -1;
    })
  }
  endDateSort() {
    this.projects
    .sort((a: IProject, b: IProject) => {
      return a.endDate > b.endDate ? 0 : -1;
    })
  }
  prioritySort() {
    this.projects
      .sort((a: IProject, b: IProject) => {
        return a.priority > b.priority ? 0 : -1;
      })
  }
  completedSort() {
    this.projects
      .sort((a: IProject, b: IProject) => {
        return a.taskCompletedCount > b.taskCompletedCount ? 0 : -1;
      })
  }
}