
export interface IParentTask {
    taskId: number,
    endDate: Date,
    priority: number,
    startDate: Date,
    status: string,
    title: string,
    parentId: number,
    projectId: number
}