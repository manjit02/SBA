import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule }from "@angular/forms";
import { DatePipe } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule , routingComponents} from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskFilterPipe } from './task-filter.pipe';
import { HttpClientModule } from '@angular/common/http';
import { TaskManagerService } from './task-manager.service';
import { NgbdModalBasic } from './modal-basic';
import { NgbdModalParent } from './modal-parent';
import { NgbdModalUser } from './modal-user';
import { UserFilterPipe } from './user-filter.pipe';
import { ProjectFilterPipe } from './project-filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TaskFilterPipe,
    routingComponents,
    NgbdModalBasic,
    NgbdModalParent,
    NgbdModalUser,
    UserFilterPipe,
    ProjectFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  	HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule.forRoot()
  ],
  providers: [DatePipe, TaskManagerService],
  bootstrap: [AppComponent]
})
export class AppModule { }