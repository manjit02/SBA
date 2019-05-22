import { Pipe, PipeTransform } from '@angular/core';
import { IUser } from 'src/app/user';

@Pipe({
  name: 'userFilter'
})
export class UserFilterPipe implements PipeTransform {

  transform(users: IUser[], searchTerm: string ) {
    if(searchTerm===undefined || searchTerm==''){
        return users;
    }
    return users.filter(user =>
     user.firstName.indexOf(searchTerm) !=-1);
  }
}
