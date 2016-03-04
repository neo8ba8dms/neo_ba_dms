angular.module('dmsApp').factory('organisationRoleService', function($resource){

    return $resource('http://localhost:8080/api/organisationRole/:id', {id: '@graphId'}, {
        update: {method: 'PUT',
            transformRequest: function(organisationRole){
                console.log(organisationRole);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < organisationRole.referes_to.length; i++){
                    var newDocument = {};
                    newDocument.graphId = organisationRole.referes_to[i].graphId;
                    organisationRole.referes_to[i] = newDocument;
                }
                var newOrganisation = {};
                newOrganisation.graphId = organisationRole.is_role_of.graphId;
                organisationRole.is_role_of = newOrganisation;
                //end handle reduce relationship-endnotes to graphId
                console.log(organisationRole);
                return angular.toJson(organisationRole);
            }, transformResponse: function(data){
                return JSOG.parse(data);
            }},
        save: {method: 'POST',
            transformRequest: function(organisationRole){
                console.log(organisationRole);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < organisationRole.referes_to.length; i++){
                    var newDocument = {};
                    newDocument.graphId = organisationRole.referes_to[i].graphId;
                    organisationRole.referes_to[i] = newDocument;
                }
                var newOrganisation = {};
                newOrganisation.graphId = organisationRole.is_role_of.graphId;
                organisationRole.is_role_of = newOrganisation;
                //end handle reduce relationship-endnotes to graphId
                console.log(organisationRole);
                return angular.toJson(organisationRole);
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


