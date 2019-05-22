import { IProject } from 'src/app/project';
import { IParent } from 'src/app/parent';
export interface ITask {
    taskId: number,
    endDate: Date,
    priority: number,
    startDate: Date,
    status: string,
    title: string,
    parentId: number,
    projectId: number,
	project: IProject,
    parentTask: IParent,
    parent: boolean,
    userId: number
}