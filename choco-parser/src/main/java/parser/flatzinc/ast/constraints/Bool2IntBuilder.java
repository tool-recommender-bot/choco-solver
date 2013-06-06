/**
 *  Copyright (c) 1999-2011, Ecole des Mines de Nantes
 *  All rights reserved.
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Ecole des Mines de Nantes nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package parser.flatzinc.ast.constraints;

import parser.flatzinc.ast.Datas;
import parser.flatzinc.ast.expression.EAnnotation;
import parser.flatzinc.ast.expression.Expression;
import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.ICF;
import solver.variables.BoolVar;
import solver.variables.IntVar;

import java.util.List;

/**
 * (a = b)
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 26/01/11
 */
public class Bool2IntBuilder implements IBuilder {

//    private static Constraint[] NO_CSTR = new Constraint[0];

    @Override
    public Constraint[] build(Solver solver, String name, List<Expression> exps, List<EAnnotation> annotations, Datas datas) {
        BoolVar a = exps.get(0).boolVarValue(solver);
        IntVar b = exps.get(1).intVarValue(solver);
        String vname = b.getName();
        return new Constraint[]{ICF.arithm(a, "=", b)};
        /*
        TODO: fix
        if (b.getNbProps() > 0) {
            //throw new FZNException("unable to remove " + b + ": it already exists in propagator(s).");
        }
        LoggerFactory.getLogger("solver").info("{} -> {} // {}: {}", new Object[]{a, b, vname, datas.get(vname)});
        solver.unassociates(b);
        datas.register(vname, a);
        LoggerFactory.getLogger("solver").info("{} -> {} // {}: {}", new Object[]{a, b, vname, datas.get(vname)});
        if (b.instantiated()) {
            try {
                a.instantiateTo(b.getValue(), Cause.Null);
            } catch (ContradictionException e) {
                throw new FZNException("unable to instantiate " + a + " to " + b.getValue() + ".");
            }
        }
        return NO_CSTR;*/
    }
}
