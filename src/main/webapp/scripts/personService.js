angular.module('dmsApp').factory('personService', function($resource){

    return $resource('http://localhost:8080/api/person/:id', {id: '@graphId'}, {
        update: {method: 'PUT',
            transformRequest: function(person){
                console.log(person);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < person.person_organisation_relationships.length; i++){
                    var newPersonOrganisationRelationshipEndNode = {};
                    newPersonOrganisationRelationshipEndNode.graphId =
                        person.person_organisation_relationships[i].relating_party.graphId;
                    person.person_organisation_relationships[i].relating_party =
                        newPersonOrganisationRelationshipEndNode;
                }
                for(var i = 0; i < person.person_person_relationships.length; i++){
                    var newPersonPersonRelationshipEndNode = {};
                    newPersonPersonRelationshipEndNode.graphId =
                        person.person_person_relationships[i].relating_party.graphId;
                    person.person_person_relationships[i].relating_party =
                        newPersonPersonRelationshipEndNode;
                }
                //end handle reduce relationship-endnotes to graphId
                console.log(person);
                return angular.toJson(person);
            }, transformResponse: function(data){
                return JSOG.parse(data);
            }},
        save: {method: 'POST',
            transformRequest: function(person){
                console.log(person);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < person.person_organisation_relationships.length; i++){
                    var newPersonOrganisationRelationshipEndNode = {};
                    newPersonOrganisationRelationshipEndNode.graphId =
                        person.person_organisation_relationships[i].relating_party.graphId;
                    person.person_organisation_relationships[i].relating_party =
                        newPersonOrganisationRelationshipEndNode;
                }
                for(var i = 0; i < person.person_person_relationships.length; i++){
                    var newPersonPersonRelationshipEndNode = {};
                    newPersonPersonRelationshipEndNode.graphId =
                        person.person_person_relationships[i].relating_party.graphId;
                    person.person_person_relationships[i].relating_party =
                        newPersonPersonRelationshipEndNode;
                }
                //end handle reduce relationship-endnotes to graphId
                console.log(person);
                return angular.toJson(person);
            }},
        get: {method: 'GET',
        transformResponse: function(data){
            var shouldBeDecoded = JSOG.parse(data);
            console.log(shouldBeDecoded);
            return shouldBeDecoded;
        }},
        query: {transformResponse: function(data){
            var shouldBeDecoded = JSOG.parse(data);
            console.log(shouldBeDecoded);
            return shouldBeDecoded;
        },isArray:true}

    });


});


