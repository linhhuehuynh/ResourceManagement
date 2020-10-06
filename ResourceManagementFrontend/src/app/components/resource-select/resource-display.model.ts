import { Resource } from '../resource/resource-data.model';
import { ResourceItem } from '../resource/resource-item/resource-item.model';

export class ResourceDisplay {
    resource: Resource;
    itemList: ResourceItem[];
    chosen: boolean;
} 