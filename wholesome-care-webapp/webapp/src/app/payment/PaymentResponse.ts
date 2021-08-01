export class PaymentResponse {
    emailId: string;
    activeStatus: boolean;
    plan : string;
    chargeId : string;
    constructor() {
        this.emailId = '';
        this.activeStatus = false;
        this.plan = '';
        this.chargeId = '';
    }
}