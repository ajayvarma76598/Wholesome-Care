package com.stackroute.graphqueryservice.Repository;

import com.stackroute.graphqueryservice.Model.Activities;
import com.stackroute.graphqueryservice.Model.FoodItems;
import com.stackroute.graphqueryservice.Model.Relationship;
import com.stackroute.graphqueryservice.Model.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public interface RelationshipRepository extends Neo4jRepository<Relationship,Long> {

    @Query("MATCH(diet:Diet) MATCH(weight: Weight)  CREATE (diet)-[:part_of]->(weight)")
    public void dietWeightLossRelationship();

    @Query("MATCH (a:physical_level) MATCH(b:UserNode {email: $email }) WHERE toInteger(a.Id) = b.physical_level CREATE (b)-[r:physical_measure]->(a)")
    public void userToPhysicalLevelRelationship(String email);

    @Query("MATCH (a:mental_level) MATCH(b:UserNode {email: $email }) WHERE toInteger(a.Id) = b.mental_level CREATE (b)-[r:mental_measure]->(a)")
    public void userToMentalLevelRelationship(String email);

    @Query("MATCH (a:diet_level) MATCH(b:UserNode {email: $email }) WHERE toInteger(a.Id) = b.diet_level CREATE (b)-[r:diet_measure]->(a)")
    public void userToDietLevelRelationship(String email);


    @Query("MATCH (a: $level)-[:isA]->(b: wellness_measure {name: $wellness_measure }) RETURN a.name")
    public List<String> getAllSubNodesOfParticularWellnessMeasure(String wellness_measure, String level);



//    @Query("MATCH(:UserNode {email: 's10@gmail.com' })-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities) MATCH(:UserNode {email: 's10@gmail.com'})-[:mental_measure]->(:mental_level)-[:contains_activity]->(d:Activities) RETURN c as Activities")
//    public List<Activities> getAllActivitiesInTime(String email);

//    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email='s10@gmail.com' RETURN { name:c.name ,activityType: c.activityType, duration_min: c.duration_min, description:c.description, calorieBurnt:c.calorieBurnt, imageUrl: c.imageUrl, videoUrl: c.videoUrl, audioUrl:c.audioUrl}")
//    public List<Activities> getAllActivitiesInTime(String email);
@Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN c as Activities")
public List<Activities> getAllActivitiesInTime(String email);

    @Query("MATCH(:UserNode {email: $email })-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c as FoodItems")
    public List<FoodItems> getFoodItemsInTime(String email, String time);

    @Query("MATCH (n:UserNode {email: $email}) RETURN n")
    public  UserNode getUserByEmail(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN count (*)")
    public int countOfGetAllActivitiesInTime(String email);

//    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email='s10@gmail.com' RETURN  c.name ,c.activityType,c.duration_min, c.description, c.calorieBurnt, c.imageUrl, c.videoUrl, c.audioUrl")
//    public List<String> getAllActivitiesAsString(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.name")
    public List<String> getAllActivitiesAsStringName(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.activityType")
    public List<String> getAllActivitiesAsStringActivityType(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.duration_min")
    public List<String> getAllActivitiesAsDuration(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.description")
    public List<String> getAllActivitiesAsStringDescription(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.calorieBurnt")
    public List<String> getAllActivitiesAsStringCalorie(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.imageUrl")
    public List<String> getAllActivitiesAsStringImageUrl(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.videoUrl")
    public List<String> getAllActivitiesAsStringVideoUrl(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.audioUrl")
    public List<String> getAllActivitiesAsStringAudioUrl(String email);

    @Query("MATCH(u :UserNode)-[:physical_measure]->(:physical_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.quotationImageUrl")
    public List<String> getAllActivitiesAsStringQuotationUrl(String email);


    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN count (*)")
    public int countOfGetAllMentalActivitiesInTime(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.name")
    public List<String> getAllMentalActivitiesAsStringName(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.activityType")
    public List<String> getAllMentalActivitiesAsStringActivityType(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.duration_min")
    public List<String> getAllMentalActivitiesAsDuration(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.description")
    public List<String> getAllMentalActivitiesAsStringDescription(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.calorieBurnt")
    public List<String> getAllMentalActivitiesAsStringCalorie(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN  c.imageUrl")
    public List<String> getAllMentalActivitiesAsStringImageUrl(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.videoUrl")
    public List<String> getAllMentalActivitiesAsStringVideoUrl(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.audioUrl")
    public List<String> getAllMentalActivitiesAsStringAudioUrl(String email);

    @Query("MATCH(u :UserNode)-[:mental_measure]->(:mental_level)-[:contains_activity]->(c:Activities)  WHERE u.email=$email RETURN   c.quotationImageUrl")
    public List<String> getAllMentalActivitiesAsStringQuotationUrl(String email);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(t:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN count(*)")
    public int countTimeNodesForUser(String email, String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.name")
    public List<String> getAllNodeNames(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.quantity")
    public List<String> getAllNodeQuantity(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.unit")
    public List<String> getAllNodeUnit(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.carbs")
    public List<String> getAllNodeCarbs(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.protein")
    public List<String> getAllNodeProtein(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.calorie")
    public List<String> getAllNodeCalorie(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.sugar")
    public List<String> getAllNodeSugar(String email,String time);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(:diet_level)-[:hasA]->(:time {name: $time})-[:contains_items]->(c:FoodItems) RETURN c.imageUrl")
    public List<String> getAllNodeImageUrl(String email,String time);

    @Query("MATCH(u:UserNode {email: $email}) RETURN u.name")
    public String getUserName(String email);

    @Query("MATCH(u :UserNode {email: $email})-[:physical_measure]->(p:physical_level) RETURN p.name")
    public String getUserphysicalLevel(String email);

    @Query("MATCH(u:UserNode {email: $email}) RETURN u.date")
    public Date getUserDate(String email);

    @Query("MATCH(u:UserNode {email: $email}) RETURN u.age")
    public String getUserAge(String email);

    @Query("MATCH(u:UserNode {email: $email}) RETURN u.place")
    public String getUserPlace(String email);

    @Query("MATCH(u:UserNode {email: $email}) RETURN u.imageUrl")
    public String getUserImageUrl(String email);

    @Query("MATCH(u :UserNode {email: $email})-[:mental_measure]->(p:mental_level) RETURN p.name")
    public  String getUserMentalLevel(String email);

    @Query("MATCH(:UserNode {email: $email})-[:diet_measure]->(d:diet_level) RETURN d.name")
    public String getUserDietLevel(String email);

    @Query("MATCH(d:UserNode {email: $email}) RETURN d.planType")
    public String getUserPlanType(String email);

    @Query("MATCH(d:UserNode {email: $email}) RETURN d.physicalScore")
    public double getUserPhysicalScore(String email);

    @Query("MATCH(d:UserNode {email: $email}) RETURN d.mentalScore")
    public double  getUserMentalScore(String email);

    @Query("MATCH(d:UserNode {email: $email}) RETURN d.dietScore")
    public double getUserDietScore(String email);


//    public List<String> getAllActivitiesAsStringName(String email);

    @Query(" MATCH (u:UserNode {email:$fromEmail}) MATCH (s:UserNode {email: $toEmail}) CREATE (u)-[r:follows]->(s)")
    public void createRelationshipBetweenUsers(String fromEmail,String toEmail);

    @Query(" MATCH (u:UserNode {email:$fromEmail})-[r:follows]->(s:UserNode {email: $toEmail}) DELETE r")
    public void removeRelationshipBetweenUsers(String fromEmail,String toEmail);

    @Query("MATCH (u:UserNode {email: $email})<-[r:follows]-(s:UserNode) RETURN s.email")
    public List<String> followers(String email);

    @Query("MATCH (u:UserNode {email: $email})-[r:follows]->(s:UserNode) RETURN s.email")
    public List<String> following(String email);

    @Query("MATCH(u:UserNode {email: $email})-[:physical_measure]->(x:physical_level) MATCH(v:UserNode)-[:physical_measure]->(y:physical_level) MATCH(u:UserNode {email:$email})-[:mental_measure]->(q:mental_level) MATCH(v:UserNode)-[:mental_measure]->(r:mental_level) MATCH(u:UserNode {email:$email})-[:diet_measure]->(s:diet_level) MATCH(v:UserNode)-[:diet_measure]->(t:diet_level) WHERE x.Id=y.Id AND q.Id=r.Id AND s.Id=t.Id AND NOT v.email CONTAINS $email AND NOT EXISTS{(u)-[:follows]->(v)}   RETURN  v.email")
    public List<String> suggestions(String email);

    @Query("MATCH (u:UserNode {email: $email})-[r:has_mentor]->(s:Mentor) RETURN s.emailId")
    public List<String> getMentorsEmail(String email);

    @Query("MATCH (u:UserNode {email: $email})-[r:has_mentor]->(s:Mentor) RETURN s.name")
    public List<String> getMentorsName(String email);

    @Query("MATCH (u:UserNode {email: $email})-[r:has_mentor]->(s:Mentor) RETURN s.expertize")
    public List<String> getMentorsExpertize(String email);

}
