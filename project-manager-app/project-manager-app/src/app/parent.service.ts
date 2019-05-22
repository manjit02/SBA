import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { IParent } from 'src/app/parent';
import { Observable } from "rxjs";
import { catchError, map } from 'rxjs/operators';
import { FormGroup, FormControl } from '@angular/forms';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ParentService {

  private _url = "http://localhost:8082/finalsba/";
  constructor(private http: HttpClient) { }
  form: FormGroup = new FormGroup({
    $key: new FormControl(),
    parentTitle: new FormControl(''),
    parentId: new FormControl(''),
    parentTaskId: new FormControl('')
  })

  getParentList(): Observable<IParent[]> {
    return this.http.get<IParent[]>(this._url + "allparent")
      .pipe(map(data => {
        return data;
      }),
      catchError(error => {
        return Observable.throw('Something went wrong ;)');
      })
      );
  } 

  getParent(id: number): Observable<IParent> {
    return this.http.get<IParent>(this._url + "viewparent/" + id);
  }

  addParent(parent){
    let body= JSON.stringify(parent);
    return this.http.post(this._url + "createparent",body,httpOptions);
  }
}