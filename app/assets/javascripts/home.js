var dataSources = {
	gene: ['ncbi', 'ensembl', 'uniprot'],
	human: ['clinvar', 'gwas'],
	modelOrganisms: []
};

var defaultDataSources = {
	gene: ['ncbi'],
	human: [],
	modelOrganisms: []
};

socket = new WebSocket('ws://echo.websocket.org');
socket.onopen = function() {
	console.log('open');
	// load the default data
	socket.send(JSON.stringify({msg: 'test', cat: 'meow'}));
};
socket.onmessage = function(e) {
 	console.log('message', e);
 	processIncomingData(e);
};
socket.onclose = function() {
 	console.log('close');
};

function processIncomingData(e) {
	var dataItemTemplate = $('#dataItemTemplate').html();
	var renderData = {
		dataSourceName: e.data.dataSourceName,
		data: e.data.data
	};

	var rendered = Mustache.render(dataItemTemplate, renderData);

	var sectionName = e.data.requester;
	$('#' + sectionName + 'Info').append(rendered);
}

function reloadSection(sectionName) {
	// get all of the selected checkboxes
	var dataSources = _.map($('#' + sectionName + 'InfoSelectors input:checked'), function(checkbox) {
		return $(checkbox).data('name');
	});

	if(dataSources.length == 0) {
		// don't try anything if there's nothing to display
		return;
	}

	// generate the request
	var toSend = {
		dataSources: dataSources,
		sectionName: sectionName,
		geneName: $('#geneInput').val()
	};

	console.log('toSend', toSend);

	// send it offfffffff
	socket.send(toSend);

	// clear the section
	$('#' + sectionName + 'Info').empty();

	// ------ testing
	var returned = {
 		data: {
	 		dataSourceName: 'ncbi',
	 		requester: sectionName,
	 		data: 'a lot of stuff'
 		}
 	};
 	processIncomingData(returned);
 	// ------ testing

	
}

function selectGene() {
	_.forEach(dataSources, function(sources, sectionName) {
		console.log('????');
		reloadSection(sectionName);
	});
}

function generatePage() {
	var selectorsTemplate = $('#selectorsTemplate').html();
    Mustache.parse(selectorsTemplate);
    // not used here, but done for performance later
    var dataItemTemplate = $('#dataItemTemplate').html();
    Mustache.parse(dataItemTemplate);

    _.forEach(dataSources, function(sources, sectionName) {
    	var checkboxData = _.map(sources, function(source) {
    		return {
    			name: source,
    			checked: _.includes(defaultDataSources[sectionName], source) ? 'checked' : ''
    		};
    	});

    	var renderData = {
    		checkboxItems: checkboxData,
    		section: sectionName
    	};

    	var rendered = Mustache.render(selectorsTemplate, renderData);
    	$('#' + sectionName + 'InfoSelectors').append(rendered);
    });

    // make the initial calls?
}