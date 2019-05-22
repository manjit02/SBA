import { Pipe, PipeTransform } from '@angular/core';
import { ITask } from 'src/app/task';

@Pipe({
  name: 'taskFilter'
})
export class TaskFilterPipe implements PipeTransform {

  transform(tasks: ITask[], searchTerm: string ) {
    if(searchTerm===undefined || searchTerm==''){
        return tasks;
    }
    return tasks.filter(task =>
     task.project.title.indexOf(searchTerm) !=-1);
  }
}