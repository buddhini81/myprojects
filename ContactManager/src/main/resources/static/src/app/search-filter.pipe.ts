import { Pipe, PipeTransform } from '@angular/core';
import { isNull } from 'util';

@Pipe({
  name: 'filter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(items: any[], searchText: string): any[] {
    if(!items) return [];
    if(!searchText) return items;
    searchText = searchText.toLowerCase();
    return items.filter( it => {
      return it.email.toLowerCase().startsWith(searchText);
    });
   }

}
