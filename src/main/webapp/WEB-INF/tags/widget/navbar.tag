<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="navbar navbar-default">
    <div class="navbar-header">
        <a class="navbar-brand" href="/"> Quick Search : Transaction</a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <form action="/searchByDate" class="navbar-form navbar-center " role="search">
            <div class="form-group ">
                <input name="query" type="datetime" class="form-control" placeholder="Search Transaction by Date">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>


        <form action="/searchByAmountLarger" class="navbar-form navbar-center input-xlarge" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Larger Than">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/searchByAmountSmaller" class="input-xlarge navbar-form navbar-center" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Smaller Than">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <form action="/searchByAmountEquals" class="navbar-form navbar-center" role="search">
            <div class="form-group">
                <input name="query" type="number" class="form-control" placeholder="Search Transaction by Amount Equals To">
            </div>
            <button type="submit" class="btn">Search</button>
        </form>

        <a href="/add" class="btn btn-default navbar-btn navbar-right">
            <span class="glyphicon glyphicon-plus"></span>
        </a>
    </div>
</div>