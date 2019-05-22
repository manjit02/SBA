import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewTaskComponent } from './view-task/view-task.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUserComponent } from './add-user/add-user.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EditTaskComponent } from './edit-task/edit-task.component';

const routes: Routes = [
  {path: '', redirectTo: '/viewtask', pathMatch: 'full'},
  {path: 'addtask',component: AddTaskComponent},
  {path: 'viewtask', component: ViewTaskComponent},
  {path: 'addProject', component: AddProjectComponent},
  {path: 'addUser', component: AddUserComponent},
  {path: 'edittask/:id', component: EditTaskComponent},
  {path: '**', component: PageNotFoundComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[ViewTaskComponent, 
  AddTaskComponent,
  AddProjectComponent,
  AddUserComponent,
  PageNotFoundComponent,
  EditTaskComponent]