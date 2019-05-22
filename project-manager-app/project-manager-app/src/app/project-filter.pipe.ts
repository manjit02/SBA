import { Pipe, PipeTransform } from '@angular/core';
import { IProject } from 'src/app/project';

@Pipe({
  name: 'projectFilter'
})
export class ProjectFilterPipe implements PipeTransform {

  transform(projects: IProject[], searchTerm: string ) {
    if(searchTerm===undefined || searchTerm==''){
        return projects;
    }
    return projects.filter(project =>
      project.title.indexOf(searchTerm) !=-1);
  }

}
