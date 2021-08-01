import { Component, Inject, Input, OnInit } from '@angular/core';
import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
interface FoodNode {
  name: string;
  children?: FoodNode[];
}
interface MainNode {
  name: string;
  children?: FoodNode[];
}

/** Flat node with expandable and level information */
interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}

@Component({
  selector: 'app-customized-plan',
  templateUrl: './customized-plan.component.html',
  styleUrls: ['./customized-plan.component.css']
})
export class CustomizedPlanComponent implements OnInit {
  @Input() plan:any;
  ngOnInit(): void {
  }
  private _transformer = (node: FoodNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      level: level,
    };
  }

  treeControl = new FlatTreeControl<ExampleFlatNode>(
      node => node.level, node => node.expandable);

  treeFlattener = new MatTreeFlattener(
      this._transformer, node => node.level, node => node.expandable, node => node.children);

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  plans:FoodNode[]=
  [
    {
      name: 'Activities',
      children: this.list.activities
    }, {
      name: 'Diet Plan',
      children: [
        {
          name: 'Breakfast',
          children: this.list.diet_plan.breakfast
        }, {
          name: 'Morning Snacks',
          children: this.list.diet_plan.morningSnacks
        }, {
          name: 'Lunch',
          children: this.list.diet_plan.lunch
        }, {
          name: 'Evening Snacks',
          children: this.list.diet_plan.eveningSnacks
        }, {
          name: 'Dinner',
          children: this.list.diet_plan.dinner
        }
      ]
    },
  ];
days:FoodNode[]=[
  {name:'Day-1', children:this.plans},{name:'Day-2', children:this.plans},{name:'Day-3', children:this.plans},
  {name:'Day-4', children:this.plans},{name:'Day-5', children:this.plans},{name:'Day-6', children:this.plans},
  {name:'Day-7', children:this.plans}
];
  constructor(@Inject(MAT_DIALOG_DATA) private list:any) {
    // console.log(list);
    const TREE_DATA: FoodNode[] = [
      {
        name: 'Customized Wellness Plan',
        children: this.days
      }
    ];
    this.dataSource.data = TREE_DATA;
  }
  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;
}
