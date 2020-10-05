import { Resource } from './../resource-data.model';
import { ResourceCol } from './../resource-col/resource-col.model';

export class ResourceItem {
    resourceExtraItemValue: string;
    resource: Resource;
    resourceColumn: ResourceCol
}
