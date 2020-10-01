import { ProjectColumn } from './project-col.model';
import { ProjectRow } from './project-row.model';

export class ProjectItem {
    id: number;
    value: string;
    projectRow: ProjectRow;
    projectColumn: ProjectColumn;
    changed: boolean = false;
} 