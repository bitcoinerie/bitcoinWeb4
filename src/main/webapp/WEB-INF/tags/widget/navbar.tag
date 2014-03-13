<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="navbar navbar-default">
    <div class="navbar-header">
        <a class="navbar-brand" href="/"> Quick Search : Transaction</a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <form action="/historique/searchByDate" class="navbar-form navbar-center " role="search">
            <div class="form-group ">
                <input name="queryStart" type="datetime" datetime="YYYY-MM-DD'T'hh:mm:ss" class="form-control" > Begin Date Interval
            </div>

            <div class="form-group ">
                <input name="queryEnd" type="datetime" datetime="YYYY-MM-DD'T'hh:mm:ss" class="form-control" >  End Date Interval
            </div>

            <button type="submit" class="btn">Search</button>
        </form>

        <br>

        <form action="/historique/searchByEmetterTransaction" class="navbar-form navbar-center input-xlarge" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Emetter">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/historique/searchByRecepterTransaction" class="navbar-form navbar-center input-xlarge" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Recepter">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/historique/searchByAmountLarger" class="navbar-form navbar-center input-xlarge" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Larger Than">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/historique/searchByAmountSmaller" class="input-xlarge navbar-form navbar-center" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Smaller Than">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/historique/searchByAmountEquals" class="navbar-form navbar-center" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Equals To">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <a href="/addTransaction/add" class="btn btn-default navbar-btn navbar-right">
            <span class="glyphicon glyphicon-plus"></span>
        </a>
    </div>
</div>