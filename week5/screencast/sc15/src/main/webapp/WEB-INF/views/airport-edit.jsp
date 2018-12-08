<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ include file="layout-header.jsp" %>
<div class="col-md-6 col-md-offset-3">
<h2>
  <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
  Airport Edit
</h2>
<!--
Use taglib form to handle airport edition
      replace all form input with its taglib equivalent
-->
<f:form modelAttribute="airport">


  <div class="form-group">
    <label for="fId">Id</label>
	<!--
	   id field shoud not be etitable,
		if the form is used to create a new airport, just don't show this field
	-->

    <input type="text" value="${airport.id}" class="form-control" name="id" id="fId" disabled/>

    <div class="form-group">
        <label for="fName">Name</label>
        <f:input path="name" class="form-control" id="fName" placeholder="Name"/>
  </div>
  <div class="form-group">
    <label for="fCity">City</label>
    <f:input path="city" class="form-control" id="fCity" placeholder="City" />
  </div>
  <div class="form-group">
    <label for="fCountry">Country</label>
    <f:input path="country" class="form-control" id="fCountry" placeholder="Country" />
  </div>
  <div class="row">
	  <div class="form-group col-md-6">
		<label for="fIata">IATA</label>
		<f:input path="iata" class="form-control" id="fIata" placeholder="iata code" />
	  </div>
	  <div class="form-group col-md-6">
		<label for="fIcao">ICAO</label>
		<f:input path="icao" class="form-control" id="fIcao" placeholder="icao code" />
	  </div>
  </div>
  <div class="form-group">
    <label for="fLatitude">Latitude</label>
    <f:input path="latitude" class="form-control" id="fLatitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fLongitude">Longitude</label>
    <f:input path="longitude" class="form-control" id="fLongitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fAltitude">Altitude (ft)</label>
    <f:input path="altitude" class="form-control" id="fAltitude" placeholder="(feet)" />
  </div>
  <div class="form-group">
    <label for="fTimezone">Timezone decay</label>
    <f:input path="timezone" class="form-control" id="fTimezone" placeholder="(hours)" />
  </div>
  <div class="form-group">
    <label for="fTz">Timezone name</label>
    <f:input path="tzName" class="form-control" id="fTz" placeholder="eg. Europe/Paris" />
  </div>
  <div class="form-group">
    <label for="fDaylight">Daylight saving time zone</label>
    <f:select path="daylight" class="form-control" id="fDaylight" placeholder="">
        <f:option value="N">N (None)</f:option>
        <f:option value="E">E (Europe)</f:option>
        <f:option value="A">A (US/Canada</f:option>
        <f:option value="S">S (South America)</f:option>
        <f:option value="O">O (Australia)</f:option>
        <f:option value="Z">Z (New Zealand)</f:option>
        <f:option value="U">U (Unknown)</f:option>
    </f:select>
  </div>

  <button type="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
    Ok
  </button>

</f:form>
</div>
<%@ include file="layout-footer.jsp" %>