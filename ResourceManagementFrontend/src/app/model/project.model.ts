import { ProjectRow } from './project-row.model';
import { ProjectColumn } from './project-col.model'
export class Project {
    id: number;
    name: string;
    createDate: Date;
    updateDate: Date;
    projectColumns: ProjectColumn[];
    projectRows: ProjectRow[];
    resourceList: any;
}