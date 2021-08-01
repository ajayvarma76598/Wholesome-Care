export class UserProfile {
  email:string;
  name:string;
  creationDate:string;
  age:string;
  place:string;
  imageUrl:string;
  planType:string;
  physicalLevel:string;
  mentalLevel:string;
  dietLevel:string;
  followers:Array<any>;
  following:Array<any>;
  mentorList:Array<any>;
  plan:{activities:Array<any>, activitiesDone:Array<any>, diet_plan:any};
  days:Array<{localDate:string, plan:{activities:Array<any>, activitiesDone:Array<any>, diet_plan:any}}>;
  constructor(email:string, name:string, creationDate:string, age:string, place:string, imageUrl:string, planType:string,
    physicalLevel:string, mentalLevel:string, dietLevel:string, followers:Array<any>, following:Array<any>, mentorList:Array<any>,
    plan:{activities:Array<any>, activitiesDone:Array<any>, diet_plan:any}, days:Array<{localDate:string, plan:{activities:Array<any>, activitiesDone:Array<any>, diet_plan:any}}>){
      this.email=email;
      this.name=name;
      this.creationDate=creationDate;
      this.age=age;
      this.place=place;
      this.imageUrl=imageUrl;
      this.planType=planType;
      this.physicalLevel=physicalLevel;
      this.mentalLevel=mentalLevel;
      this.dietLevel=dietLevel;
      this.followers=followers;
      this.following=following;
      this.mentorList=mentorList;
      this.plan=plan;
      this.days=days;
  }
}
