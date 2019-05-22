import { Injectable ,EventEmitter} from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { ITask } from 'src/app/task';
import { Observable } from "rxjs";
import { catchError, map } from 'rxjs/operators';
import { FormGroup, FormControl } from '@angular/forms';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}
@Injectable({
  providedIn: 'root'
})

export class TaskManagerService {
 
  private _url = "http://localhost:8082/finalsba/";
  taskEndedSuccess = new EventEmitter<String>();
  constructor(private http: HttpClient) { }
  form: FormGroup = new FormGroup({
    $key: new FormControl(),
    title: new FormControl(''),
    priority: new FormControl(''),
    startDate: new FormControl('')
  })

  getTaskList(): Observable<ITask[]> {
    return this.http.get<ITask[]>(this._url + "alltasks")
      .pipe(map(data => {
        return data;
      }),
      catchError(error => {
        return Observable.throw('Something went wrong ;)');
      })
      );
  }

  getTask(id: number): Observable<ITask> {
    return this.http.get<ITask>(this._url + "viewtask/" + id);
  }

  updateTask(task){
    let body= JSON.stringify(task);
    return this.http.post(this._url + "edittask/"+task.taskId,body,httpOptions);
  }
  addTask(task){
    let body= JSON.stringify(task);
    return this.http.post(this._url + "createtask",body,httpOptions);
  }
  endTask(taskId:number){
    console.log(this._url + "delete/id/"+taskId);
    return this.http.delete(this._url + "deleteTask/id/"+taskId).toPromise();
  }
}