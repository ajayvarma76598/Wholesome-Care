import { AppointmentSlot } from "./AppointmentSlot";

export class BookedAppointment{
    appointmentSlot:AppointmentSlot;
    customerEmail:String;
    customerName:String;
    // dateTime:Date;
    constructor(){
        this.appointmentSlot=new AppointmentSlot();
        this.customerEmail='';
        this.customerName='';
        // this.dateTime = new Date();
    }
}