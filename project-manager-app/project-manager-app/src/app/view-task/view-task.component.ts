import { Component, OnInit } from '@angular/core';
import { TaskManagerService } from 'src/app/task-manager.service';
import { Observable } from 'rxjs';
import { ITask } from 'src/app/task';
import { NgbdModalBasic } from 'src/app/modal-basic';
import { ProjectService } from 'src/app/project.service';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  public tasks: ITask[] = [];
  constructor(private _taskManagerService: TaskManagerService, private _projectService: ProjectService) {
    this._taskManagerService.taskEndedSuccess.subscribe(
      (st: string) => {
        console.log("Hii in constructor");
        this.getAllTasks();
      }
    )
  }
  public filterProject;
  searchTerm: string;

  ngOnInit() {
    this.getAllTasks();
  }
  getAllTasks() {
    this._taskManagerService.getTaskList()
      .subscribe(data => this.tasks = data);
  }
  endTask(taskId) {
    this._taskManagerService.endTask(taskId);
    this._taskManagerService.taskEndedSuccess.emit('Task ended successfully');
  }
  prioritySort() {
    this.tasks
      .sort((a: ITask, b: ITask) => {
        return a.priority.valueOf() - b.priority.valueOf();
      })
  }
  startDateSort() {
    this.tasks
      .sort((a: ITask, b: ITask) => {
        return a.startDate > b.startDate ? 0 : -1;
      })
  }
  endDateSort() {
    this.tasks
      .sort((a: ITask, b: ITask) => {
        return a.endDate > b.endDate ? 0 : -1;
      })
  }
  getFilterProject(id: number) {
    var _that = this;
    this._projectService.getProject(id).subscribe(
      data => {
        _that.filterProject = data
        this.updateFormField();
      },
      err => console.error(err),
      () => console.log('Project Loaded for View'));
  }
  receiveMessage($event) {
    console.log($event);
    if($event){
      this.getFilterProject($event);
    }else{
      this.searchTerm ="";
    }
  }
  updateFormField(){
    this.searchTerm = this.filterProject.title;
  }
}
