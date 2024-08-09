# Showcase for a behavioural change in spring data rest

Showcase for different behaviour of spring data rest in spring boot 3.3.2 than in 3.2.7. 

In Short:
* In an entity-graph, with 3 entities and the "middle" one not being exposed by spring data rest, it is now not possible anymore, to change the relation to another entity.

Rough ERD:
SchoolClass -1---n- Pupil -n---1- Course

SchoolClass and Course are exposed via spring data rest, Pupil is not.
We did not find a way to manage the relationship between Pupil and Course through the spring data rest endpoint for SchoolClass.

This was possible in former versions of spring data rest.