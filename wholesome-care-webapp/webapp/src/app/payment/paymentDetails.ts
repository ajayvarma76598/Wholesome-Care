export class PaymentDetails{
    emailId:String;
    card_number:String;
    exp_month: String;
    exp_year: String;
    cvc: String;
    plan: String;

    constructor(){
        this.emailId = '',
        this.card_number = '';
        this.plan = '';
        this.cvc = '';
        this.exp_month = '';
        this.exp_year = ''
    }
}