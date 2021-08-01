import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NutritionComponent } from './nutrition/nutrition.component';
import { PaymentComponent } from './payment/payment.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'
import { HomeComponent } from './home/home.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-mentor/board-moderator.component';
//import { ForgetComponent } from './forget/forget.component';
// import { ForgetComponent } from './forget/forget.component';
import { QuestionnaireComponent } from './questionnaire/questionnaire.component';
import { GuestDetailsComponent } from './guest-details/guest-details.component';
import { BMICalculatorComponent } from './bmicalculator/bmicalculator.component';
import { ProfileComponent } from './profile/profile.component';
import { PricingComponent } from './pricing/pricing.component';
import { SamplePlanComponent } from './sample-plan/sample-plan.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { SentimentComponent } from './sentiment/sentiment.component';
import { BlogsComponent } from './blogs/blogs.component';
import { MentorsComponent } from './mentors/mentors.component';
import { RoutingGaurdGuard } from './routing-gaurd.guard';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { HelplineComponent } from './helpline/helpline.component';
// import { ListComponent } from './list/list.component';
import { MealPlanComponent } from './meal-plan/meal-plan.component';
import { BeforeDashboardComponent } from './before-dashboard/before-dashboard.component';
import { MentorProfileComponent } from './mentor-profile/mentor-profile.component';
import { MentorDashboardComponent } from './mentor-dashboard/mentor-dashboard.component';
import { PageComponent } from './page/page.component';
const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {component: NutritionComponent, path: 'chatbot'},
  {component: QuestionnaireComponent, path: 'questionnaire'},
  {component: PaymentComponent, path: 'payment',
  canActivate:[RoutingGaurdGuard]
},
  {path: 'guest', component: GuestDetailsComponent},
{path:'blogs', component:BlogsComponent},
{path:'mentors/:index',component:MentorsComponent},
{path:'mentors',component:MentorsComponent},
  { path: 'home', component: HomeComponent },
    // { path: 'login', component: LoginComponent },
    {path:'bmicalculator', component: BMICalculatorComponent},
    {path:'sampleplan', component: SamplePlanComponent},
    {path: 'dashboard', component: DashboardComponent},
{path:'landing', component:LandingPageComponent},
    // { path: 'register', component: RegisterComponent },
    { path: 'api/user', component: BoardUserComponent },
    { path: 'api/mentor', component: BoardModeratorComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    {path:'profile', component:ProfileComponent},
    {path:'userProfile', component:UserProfileComponent},
    {path:'mealplan',component: MealPlanComponent},
    {path:'pricing',component:PricingComponent},
    {path:'analysis',component:SentimentComponent},
    {path :'help',component: HelplineComponent },
    {path :'login',component: PageComponent },

    {path:"mentorProfile",component:MentorProfileComponent},
    {
      path:'mentorDashboard',component:MentorDashboardComponent
    },
    {
      path:'payment/:plan',
      component: PaymentComponent,
      canActivate:[RoutingGaurdGuard]
    },
    {path :'beforeDashBoard',component: BeforeDashboardComponent },
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
