angular.module('dmsApp').factory('personRoleService', function($resource){

    return $resource('http://localhost:8080/api/personRole/:id', {id: '@graphId'}, {
        update: {method: 'PUT',
            transformRequest: function(personRole){
                console.log(personRole);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < personRole.referes_to.length; i++){
                    var newDocument = {};
                    newDocument.graphId = personRole.referes_to[i].graphId;
                    personRole.referes_to[i] = newDocument;
                }
                var newPerson = {};
                newPerson.graphId = personRole.is_role_of.graphId;
                personRole.is_role_of = newPerson;
                //end handle reduce relationship-endnotes to graphId
                console.log(personRole);
                return angular.toJson(personRole);
            }, transformResponse: function(data){
                return JSOG.parse(data);
            }},
        save: {method: 'POST',
            transformRequest: function(personRole){
                console.log(personRole);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < personRole.referes_to.length; i++){
                    var newDocument = {};
                    newDocument.graphId = personRole.referes_to[i].graphId;
                    personRole.referes_to[i] = newDocument;
                }
                var newPerson = {};
                newPerson.graphId = personRole.is_role_of.graphId;
                personRole.is_role_of = newPerson;
                //end handle reduce relationship-endnotes to graphId
                console.log(personRole);
                return angular.toJson(personRole);
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


