#
@Data  //  generates getters, setters, equals, toString & hashCode we well
@Builder  // generate builder for the fields within
@NoArgsConstructor // generates a no argument constructor
@AllArgsConstructor // generates a constructor with all arguments
@ToString(exclude = {"posts"}) //  generates toString method, skipping passed field as name
@EqualsAndHashCode(exclude = {"posts"}) // generates equals and hashCode methods, skipping passed fields
#
/*
'guid' is useful to hide incremental 'id' from all external communications for security reasons.
'id' would be faster to index and query while doing pagination, filtering etc.

As good practice, it is better not to send back an incremental ID and primary key to the client for any object. Thus, GUIDs (Global Unique 
Identifiers) can be used. However, performance can be slow, as indexing big random characters is going to be slow. To overcome this, 
we use both IDs.
*/
@org.hibernate.annotations.Type(type = "pg-uuid")
UUID guid;
#
The annotations @JsonIgnore, @ToString(exclude = {"posts"}), and @EqualsAndHashCode(exclude = {"posts"})help in ignoring 
lazy loaded related objects from serialization, generated toString, equals, and hashCodeobject comparison methods.

#
The industry use cases and solutions with Spring Data JPA:
•  Multi datasource interaction
•  Solving the N+1 problem
•  Native queries with results mapped to DTOs using constructor
•  Native queries with results mapped to projections
•  The Spring Converter Service role in native queries
•  Concepts like entity lifecycle, ACID properties, CAP Theorem, and isolation levels

Technically, this is going to involve:
•  Creating separate datasource properties for connections.
•  Creating two datasource and entityManagerfactory beans.
•  Creating data or domain models and repositories, in different packages, for clarity. Entity classes and repositories are not required to be in different packages, but it’s better to keep it consistent.

Solving the N+1 Problem
A common problem we generally hear about with respect to ORMs is the N+1 problem. 
This is a misconception that N+1 problem happens only with ORMs. Rather, this is a 
generic data access issue that can happen while fetching data from RDBMS. Considering 
the example from the previous section, if we want to fetch a list of users along with 
their written posts, by default Hibernate is going to fire one query to fetch all the users 
and then N queries to fetch POSTs of each returned user object. This scenario is called 
the N+1 problem. If you are fetching data via JDBC in the form of a parent object list 
containing a child object list, the same problem persists there. There are three ways to 
query with JPA on entities with associations to overcome this problem:
•  Fetch using the left fetch joinclause in JPQL
•  Fetch user objects first and then fetch POSTobjects for these users separately
•  Fetch as a single combined result mapped to DTOs with a native query

#
#Collection Mapping
> Sometimes a collection is used like a milk crate: it’s just a simple container with no 
  apparent order or intended organization. Other cases demand some kind of system of 
  order and arranging so the way objects are retrieved from the collection has meaning. 
  Whether the collection is of the first type or the second, collections of objects require 
  more effort to map than single objects, although in compensation they offer greater 
  flexibility.
  In the last chapter, we began the journey of mapping collection-valued relationships, 
  spooning out only the basics of mapping collections of entities to the database. This 
  chapter goes into more detail about how we can map more sophisticated collection types, 
  such as persistently ordered lists, and maps with keys and values that are of various 
  object types. We even explore how to map collections of objects that are not entities.
  Relationships and Element Collections
  When we speak of mapping collections, there are actually three kinds of objects that 
  we can store in mapped collections. We can map collections of entities, embeddables, 
  or basic types, and each one requires a certain level of understanding to be correctly 
  mapped and efficiently used.
  We should clarify one potential point of confusion about these types of objects 
  when they are stored in collections. In the previous chapter, we introduced the concept 
  of relationships from one entity type to another, and you learned that when the 
  source entity has a collection containing instances of the target entity type it is called 
  a multivalued relationship. However, collections of embeddable and basic types are 
  not relationships; they are simply collections of elements that are thus called element 
  collections. Relationships define associations between independent entities, whereas 
  element collections contain objects that are dependent on the referencing entity, and 
  can be retrieved only through the entity that contains them.
  158
  A practical difference between relationships and element collections is the 
  annotation that is used to denote them. A relationship minimally requires the 
  relationship annotation, either @OneToManyor @ManyToMany, whereas an element 
  collection is indicated by the @ElementCollection annotation. Assuming the 
  VacationEntry embeddable class in Listing 5-1, Listing 5-2shows an example of an 
  element collection of embeddables in the vacationBookings attribute, as well as an 
  element collection of basic types (String) in the nickNames attribute.