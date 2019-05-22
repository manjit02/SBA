import { IParentTask } from 'src/app/parentTask';
export interface IParent {
    parentId: number,
	parentTaskId: number,
    parentTask: IParentTask
}