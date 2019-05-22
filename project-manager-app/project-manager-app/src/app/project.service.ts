import { Injectable ,EventEmitter} from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { IProject } from 'src/app/project';
import { Observable } from "rxjs";
import { catchError, map } from 'rxjs/operators';
import { FormGroup, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private _url = "http://localhost:8082/finalsba/";
  projectUpdateSuccess = new EventEmitter<String>();
  constructor(private http: HttpClient, private datePipe: DatePipe) { }
  form: FormGroup = new FormGroup({
    $key: new FormControl(),
    title: new FormControl(''),
    priority: new FormControl(''),
    startDate: new FormControl('')
  })

  getProjectList(): Observable<IProject[]> {
    return this.http.get<IProject[]>(this._url + "allprojects")
      .pipe(map(data => {
        return data;
      }),
      catchError(error => {
        return Observable.throw('Something went wrong ;)');
      })
      );
  } 
  getProject(id: number): Observable<IProject> {
    return this.http.get<IProject>(this._url + "viewproject/" + id);
  }
 transformDate(date){
 return this.datePipe.transform(date, 'yyyy-MM-dd');
 }
 createproject(project){
  let body= JSON.stringify(project);
  return this.http.post(this._url + "createproject",body,httpOptions);
 }
 suspendproject(project){
  let body= JSON.stringify(project);
  return this.http.post(this._url + "editproject/"+project.projectId,body,httpOptions);
 }
}