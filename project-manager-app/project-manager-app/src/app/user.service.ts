import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { IUser } from 'src/app/user';
import { Observable } from "rxjs";
import { catchError, map } from 'rxjs/operators';
import { FormGroup, FormControl } from '@angular/forms';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _url = "http://localhost:8082/finalsba/";
  UserUpdateSuccess = new EventEmitter<String>();
  constructor(private http: HttpClient) { }
  form: FormGroup = new FormGroup({
    $key: new FormControl(),
    title: new FormControl(''),
    priority: new FormControl(''),
    startDate: new FormControl('')
  })

  getUsersList(): Observable<IUser[]> {
    return this.http.get<IUser[]>(this._url + "activeuser")
      .pipe(map(data => {
        return data;
      }),
      catchError(error => {
        return Observable.throw('Something went wrong ;)');
      })
      );
  } 
  getUser(id: number): Observable<IUser> {
    return this.http.get<IUser>(this._url + "viewuser/" + id);
  }
  addUser(user){
    let body= JSON.stringify(user);
    return this.http.post(this._url + "createuser",body,httpOptions);
  }
  delete(id: number){
    let body= JSON.stringify(id);
    return this.http.delete(this._url + "deleteUser/id/"+id).toPromise();
  }
  updateUser(user){
    let body= JSON.stringify(user);
    return this.http.post(this._url + "edituser/"+user.userId,body,httpOptions);
  }
}