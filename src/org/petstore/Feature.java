package org.petstore;

/**
 * IMPORTANT: ****************************************************
 * This demo requires the Soplet language extension (which does not
 * exist yet hehe...)
 * You can retrieve it at www.TODO.org/TODO...
 * Otherwise you will get a bunch of compiler errors, and you will
 * not be able to run this demo
 * ***************************************************************
 * 
 * This list shows the language-related features introduced by the
 * concept of semantic oriented programming (SOP)
 * 
 * Right-click on any of them, and select References/Workspace (for 
 * Eclipse IDE), in order to see where they are used and showcased.
 * 
 * @author chrismay
 */
public enum Feature {

	/**
	 * Use Soplets to bind GUI controls (text fields, combo boxes etc.)
	 * to bind them to their corresponding entity fields.
	 * The binding helps in loading the value into the control, and to
	 * store the value introduced by the user back to the entity.
	 * 
	 * Furthermore, the binding feature may support input validation, 
	 * access control, labeling, documentation and other related functions.
	 */
	binding,
	
	/**
	 * Soplets encourage the developer to place the documentation near 
	 * the concept it wants to describe. 
	 * 
	 * It replaces the traditional JavaDoc documentation, with the benefit
	 * that it can be read by the program in run-time, and be used accordingly,
	 * e.g. for GUI documentation (tool-tip-texts etc., see feature 'binding'), 
	 * or for creating PDF or HTML documentation on the fly.
	 */
	documentation,
	
	/**
	 * An entity is a class representing a (dynamic) business object. 
	 * Typically, an entity is mapped to a corresponding table in 
	 * the database, and mapped to it via an O/R mapping tool
	 * such as JPA, Hibernate etc.
	 * 
	 * Most parts of the code representing the entity object is generated
	 * as 'virtual' code, that is as dynamically created bytecode.
	 * For more details about virtual code please refer to Project Lombok
	 * (http://projectlombok.org/features/Data.html)
	 */
	entities,
	
	/**
	 * In order to identify exceptions and correlate them to the corresponding 
	 * code, it is convenient to identify them with a unique identifier.
	 * Soplets enrich these identifiers with additional information, such
	 * as the possible cause for the exception, and a proposed solution in
	 * order to address the issue. As with any Soplet, a convenient documentation
	 * can be generated by these structures, and be provided to the server 
	 * admin personnel.
	 */
	exceptions,
	
	/**
	 * Soplets may be optionally be enriched with a functional extension 
	 * in form of a (stateless) method.
	 */
	functionsAndRules,
	
	/**
	 * In order to be used efficiently, Soplets require inheritance of 
	 * annotations and enumerations. (see the IMPORTANT note in the header
	 * for more details)
	 */
	inheritance,

	/**
	 * Soplet Lists are nothing more than 'enriched' enumerations. You typically
	 * use them for lists of data which change rarely or never. See also
	 * 'staticVsDynamic' for more details.
	 */
	lists,
	
	/**
	 * TODO (not yet implemented)
	 */
	privileges,
	
	/**
	 * Generally, data is supposed to be stored and persisted in a database.
	 * But in many cases it may result easier and more convenient to represent
	 * them hard-coded in the code. This is feasible only if the data change 
	 * never or very rarely, such as a list of countries. SopArticle is another
	 * example where this concept is illustrated.
	 * 
	 * The advantages: 
	 * - the data can be type-safe referenced in the code
	 * - no need to map related functions or contextual data (such as translations)
	 *   to the (dynamic) data
	 * - the database schema stays clean and tidy, and can be easier maintained
	 * - generally much less code is needed (no DAO, JPA etc.)
	 */
	staticVsDynamic,
	
	/**
	 * Soplets are closed and encapsulated objects, which as such represent a 
	 * given concept. However, they may be linked with other soplets, and thus
	 * creating a network of interlaced logic.
	 */
	interlaced,

	/**
	 * Use Soplets for requirement tracking, in order to see if and where a
	 * given feature is implemented
	 */
	traceability,
	
	/**
	 * Soplets can be used instead of traditional resource files for adding
	 * NLS (national language support) to an application. 
	 * 
	 * See www.freenls.com for a free online tool which supports round-trip
	 * engineering for multi-language applications
	 * 
	 */
	translations;

	
	public static @interface DemoFeature {

		public Feature value();
	}
}
