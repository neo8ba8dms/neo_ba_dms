angular.module('dmsApp').factory('organisationService', function($resource){

    return $resource('http://localhost:8080/api/organisation/:id', {id: '@graphId'}, {
        update: {method: 'PUT',
            transformRequest: function(organisation){
                console.log(organisation);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < organisation.organisation_person_relationships.length; i++){
                    var newOrganisationPersonRelationshipEndNode = {};
                    newOrganisationPersonRelationshipEndNode.graphId =
                        organisation.organisation_person_relationships[i].relating_party.graphId;
                    organisation.organisation_person_relationships[i].relating_party =
                        newOrganisationPersonRelationshipEndNode;
                }
                for(var i = 0; i < organisation.organisation_organisation_relationships.length; i++){
                    var newOrganisationOrganisationRelationshipEndNode = {};
                    newOrganisationOrganisationRelationshipEndNode.graphId =
                        organisation.organisation_organisation_relationships[i].relating_party.graphId;
                    organisation.organisation_organisation_relationships[i].relating_party =
                        newOrganisationOrganisationRelationshipEndNode;
                }
                //end handle reduce relationship-endnotes to graphId
                console.log(organisation);
                return angular.toJson(organisation);
        }, transformResponse: function(data){
                return JSOG.parse(data);
            }},
        save: {method: 'POST',
            transformRequest: function(organisation) {
                console.log(organisation);
                //handle reduce relationship-endnotes to graphId
                for (var i = 0; i < organisation.organisation_person_relationships.length; i++) {
                    var newOrganisationPersonRelationshipEndNode = {};
                    newOrganisationPersonRelationshipEndNode.graphId =
                        organisation.organisation_person_relationships[i].relating_party.graphId;
                    organisation.organisation_person_relationships[i].relating_party =
                        newOrganisationPersonRelationshipEndNode;
                }
                for (var i = 0; i < organisation.organisation_organisation_relationships.length; i++) {
                    var newOrganisationOrganisationRelationshipEndNode = {};
                    newOrganisationOrganisationRelationshipEndNode.graphId =
                        organisation.organisation_organisation_relationships[i].relating_party.graphId;
                    organisation.organisation_organisation_relationships[i].relating_party =
                        newOrganisationOrganisationRelationshipEndNode;
                }
                //end handle reduce relationship-endnotes to graphId
                console.log(organisation);
                return angular.toJson(organisation);
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


