import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/user.service';
import { IUser } from 'src/app/user';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  validMessage : string= "";
  useraddform: FormGroup;
  public users: IUser[] = [];
  public userEdit: IUser;
  showEdit:boolean =false;
  constructor(private _userService: UserService) {
    this._userService.UserUpdateSuccess.subscribe(
      (st: string) => {
        console.log("Hii in constructor");
        this.getAllUsers();
      }
    )
   }

  ngOnInit() {
    this.useraddform = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      status: new FormControl('Active', Validators.required),
      userId: new FormControl()
    });
    this.getAllUsers();
  }
  getAllUsers() {
    this._userService.getUsersList()
      .subscribe(data => this.users = data);
  }
  addUser(){  
    if(this.showEdit){
      this.userEdit.firstName=this.useraddform.value.firstName;
      this.userEdit.lastName=this.useraddform.value.lastName;
      this._userService.updateUser(this.userEdit).subscribe(
        data => {
          this._userService.UserUpdateSuccess.emit('User Updated Success');
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      )
    }else{
      this._userService.addUser(this.useraddform.value).subscribe(
        data => {       
          this._userService.UserUpdateSuccess.emit('User Updated Success'); 
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      )
    }
    this.resetForm();   
    this.validMessage ="Save Success";
  }
  editUser(id: number){
    this.showEdit=true;
    console.log(id);
    var _that = this;
    this._userService.getUser(id).subscribe(
      data => {
        _that.userEdit= data
          this.populateView();
      },
      err => console.error(err),
      () => console.log('Task Loaded for Editing'));
  }
  updateUser(){
    this.validMessage ="Create Success";
    this._userService.addUser(this.useraddform.value).subscribe(
      data => {
        this.useraddform.reset();
        this._userService.UserUpdateSuccess.emit('User Updated Success');
        return true;
      },
      error => {
        return Observable.throw(error);
      }
    )
  }
  deleteUser(id: number){
    this.validMessage ="Delete Success";
    this._userService.delete(id)
    this._userService.UserUpdateSuccess.emit('Task ended successfully');
  }
  resetForm(){
    this.useraddform = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      status: new FormControl('Active', Validators.required),
      userId: new FormControl()
    });
    this.showEdit=false;
    this.validMessage ="";
  }
  populateView(){
    this.useraddform.controls['firstName'].setValue(this.userEdit.firstName);
    this.useraddform.controls['lastName'].setValue(this.userEdit.lastName);
    console.log(this.useraddform.value);
  }
  firstNameSort() {
    this.users
      .sort((a: IUser, b: IUser) => {
        return a.firstName > b.firstName ? 0 : -1;
      })
  }
  lastNameSort() {
    this.users
      .sort((a: IUser, b: IUser) => {
        return a.lastName > b.lastName ? 0 : -1;
      })
  }
} 
